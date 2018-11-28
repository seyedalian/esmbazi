package ir.sssa.esmbazi

import android.content.Context
import java.io.*


class ShareReference {

    //writeFileOnInternalStorage -----------------------------------------------------------------writeFileOnInternalStorage
    private fun writeFileOnInternalStorage(mcoContext: Context, sFileName: String, sBody: String) {
        val file = File(mcoContext.filesDir, "sssa")
        if (!file.exists()) {
            file.mkdir()
        }

        try {
            val gpxfile = File(file, sFileName)
            val writer = FileWriter(gpxfile)
            writer.write(sBody)
            writer.flush()
            writer.close()

        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    //readFileOnInternalStorage -------------------------------------------------readFileOnInternalStorage
    private fun readFileOnInternalStorage(mcoContext: Context, sFileName: String): String? {


        //Get the text file
        val file = File(mcoContext.filesDir, "sssa")
        if (!file.exists()) {
            file.mkdir()
            return null
        }


        //Read text from file
        val text = StringBuilder()
        try {
            val gpxfile = File(file, sFileName)

            val br = BufferedReader(FileReader(gpxfile))


            val allText = br.use(BufferedReader::readText)
            text.append(allText)
            br.close()
        } catch (e: IOException) {
            //You'll need to add proper error handling here
        }

        return text.toString()

    }
}