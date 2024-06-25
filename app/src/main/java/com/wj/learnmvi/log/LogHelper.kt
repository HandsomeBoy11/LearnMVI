package com.wj.learnmvi.log

import android.R.id.message
import android.util.Log
import com.wj.learnmvi.base2.impl.MyApp
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 *  Author:WJ
 *  Date:2024/2/19 10:08
 *  Describe：
 */
object LogHelper {
    private const val TAG = "LogHelper"
    private const val KEY = "myencryptionkey" // 16字节的加密密钥，实际项目中请更换为安全的密钥
    private const val LOG_FILE = "log.txt"
    private const val AES_KEY = "secretkey1234567" // 16 characters for AES-128


    fun logToFile(logMessage: String) {
        try {
            val file = File(MyApp.getInstance().filesDir,LOG_FILE)
            val fileWriter = FileWriter(file, true) // append mode

            val writer = BufferedWriter(fileWriter)

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val timestamp = sdf.format(Date())
            val encryptedMessage = encrypt(logMessage)
//            writer.write("$timestamp \n $encryptedMessage")
            writer.write("$encryptedMessage")
            writer.newLine()
            writer.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error writing log to file: " + e.message)
        }
    }
    fun readLog(): String? {
        val logContent = StringBuilder()
        try {
            val file = File(MyApp.getInstance().filesDir,LOG_FILE)
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                println("wj==>$line")
                logContent.append(decrypt(line)).append("\n")
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return logContent.toString()
    }
//    fun readLogFile(): String? {
//        return try {
//            val file = File("log.txt")
//            // 读取加密的日志内容并解密
//            // 这里省略解密部分的代码
//            decrypt("encrypted_log_content")
//        } catch (e: IOException) {
//            Log.e(TAG, "Error reading log file: " + e.message)
//            null
//        }
//    }

    private fun encrypt(input: String): String? {
        return try {
            val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val key = SecretKeySpec(AES_KEY.toByteArray(), "AES")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encrypted: ByteArray = cipher.doFinal(input.toByteArray())
            String(encrypted)
        } catch (e: Exception) {
            Log.e(TAG, "Error encrypting log: " + e.message)
            null
        }
    }

    private fun decrypt(input: String?): String? {
        return try {
            val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val key = SecretKeySpec(AES_KEY.toByteArray(), "AES")
            cipher.init(Cipher.DECRYPT_MODE, key)
            val decrypted: ByteArray = cipher.doFinal(input?.toByteArray())
            String(decrypted)
        } catch (e: Exception) {
            Log.e(TAG, "Error decrypting log: " + e.message)
            null
        }
    }

    fun uploadLogToServer() {
        val logContent = readLog()
        if (logContent != null) {
            // 上传日志内容到服务器
            // 这里省略上传到服务器的代码
            Log.d(TAG, "Log uploaded to server: $logContent")
        } else {
            Log.e(TAG, "Error uploading log to server: log content is null")
        }
    }
}