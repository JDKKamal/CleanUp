package com.jdkgroup.customviews

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.support.annotation.RequiresPermission
import android.widget.Toast

import java.io.IOException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey

import android.content.Context.FINGERPRINT_SERVICE
import android.content.Context.KEYGUARD_SERVICE

@RequiresApi(api = Build.VERSION_CODES.M)
class FingerPrintHelper {

    private var fingerprintManager: FingerprintManager? = null
    private var keyguardManager: KeyguardManager? = null
    private var keyStore: KeyStore? = null
    private var cipher: Cipher? = null
    private var context: Context? = null
    private var isCipherReady = false
    private var callback: AuthenticationListener? = null
    private var cancellationSignal: CancellationSignal? = null

    interface AuthenticationListener {
        fun onAuthError(errorCode: Int, errorMsg: String)
        fun onAuthFailed()
        fun onAuthSuccess()
        fun onAuthCancelled()
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    @Throws(RuntimeException::class)
    fun init(context: Context) {
        this.context = context

        fingerprintManager = context.getSystemService(FINGERPRINT_SERVICE) as FingerprintManager
        keyguardManager = context.getSystemService(KEYGUARD_SERVICE) as KeyguardManager

        setupFingerprintManager()
    }

    fun stopListening() {
        if (cancellationSignal != null)
            cancellationSignal!!.cancel()
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    fun startListening(listener: AuthenticationListener) {
        callback = listener

        if (isCipherReady) {
            val cryptoObject = FingerprintManager.CryptoObject(cipher!!)
            cancellationSignal = CancellationSignal()

            fingerprintManager!!.authenticate(cryptoObject, cancellationSignal, 0, AuthCallback(), null)
        } else
            throw RuntimeException("Cipher not ready")
    }

    //only called from within init() which requires the permission
    @Throws(RuntimeException::class)
    private fun setupFingerprintManager() {
        if (!keyguardManager!!.isKeyguardSecure) {
            throw RuntimeException("Keyguard not secure")
        }

        if (!fingerprintManager!!.hasEnrolledFingerprints()) {
            throw RuntimeException("No fingerprints stored")
        }

        generateKey()
        cipherInit()
    }

    @Throws(RuntimeException::class)
    private fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")

            keyStore!!.load(null)
            keyGenerator.init(KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build())
            keyGenerator.generateKey()
        } catch (e: KeyStoreException) {
            throw RuntimeException(e.toString(), e)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e.toString(), e)
        } catch (e: NoSuchProviderException) {
            throw RuntimeException(e.toString(), e)
        } catch (e: CertificateException) {
            throw RuntimeException(e.toString(), e)
        } catch (e: IOException) {
            throw RuntimeException(e.toString(), e)
        } catch (e: InvalidAlgorithmParameterException) {
            throw RuntimeException(e.toString(), e)
        }

    }

    @Throws(RuntimeException::class)
    private fun cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7)
            keyStore!!.load(null)

            val key = keyStore!!.getKey(KEY_NAME, null) as SecretKey
            cipher!!.init(Cipher.ENCRYPT_MODE, key)

            isCipherReady = true
        } catch (e: NoSuchAlgorithmException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: NoSuchPaddingException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: IOException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: KeyStoreException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: InvalidKeyException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: UnrecoverableKeyException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        } catch (e: CertificateException) {
            isCipherReady = false
            throw RuntimeException("Failed to get Cipher", e)
        }

    }


    private inner class AuthCallback : FingerprintManager.AuthenticationCallback() {
        override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
            if (callback != null) {
                if (errMsgId == FingerprintManager.FINGERPRINT_ERROR_CANCELED)
                    callback!!.onAuthCancelled()
                else
                    callback!!.onAuthError(errMsgId, errString.toString())
            }
        }

        override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
            Toast.makeText(context, helpString, Toast.LENGTH_LONG).show()
        }

        override fun onAuthenticationFailed() {
            if (callback != null)
                callback!!.onAuthFailed()
        }

        override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
            if (callback != null)
                callback!!.onAuthSuccess()
        }
    }

    companion object {
        private val KEY_NAME = "helper_key"
    }
}
