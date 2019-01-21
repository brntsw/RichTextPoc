package k2partnering.bruno.com.br.ricktextpoc.network

import android.app.ProgressDialog
import android.net.Uri
import android.os.AsyncTask
import com.chinalwb.are.spans.AreImageSpan
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image
import java.lang.ref.WeakReference

import android.os.AsyncTask.THREAD_POOL_EXECUTOR
import com.chinalwb.are.strategies.ImageStrategy

class DemoImageStrategy : ImageStrategy {
    override fun uploadAndInsertImage(uri: Uri, areStyleImage: ARE_Style_Image) {
        UploadImageTask(areStyleImage).executeOnExecutor(THREAD_POOL_EXECUTOR, uri)
    }

    private class UploadImageTask internal constructor(styleImage: ARE_Style_Image) : AsyncTask<Uri, Int, String>() {

        internal var areStyleImage: WeakReference<ARE_Style_Image> = WeakReference(styleImage)
        private var dialog: ProgressDialog? = null

        override fun onPreExecute() {
            super.onPreExecute()
            if (dialog == null) {
                dialog = ProgressDialog.show(
                    areStyleImage.get()!!.editText.context,
                    "",
                    "Uploading image. Please wait...",
                    true
                )
            } else {
                dialog!!.show()
            }
        }

        override fun doInBackground(vararg uris: Uri): String? {
            if (uris != null && uris.size > 0) {
                try {
                    // do upload here ~
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // Returns the image url on server here
                return "https://avatars0.githubusercontent.com/u/1758864?s=460&v=4"
            }
            return null
        }

        protected override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            if (dialog != null) {
                dialog!!.dismiss()
            }
            if (areStyleImage.get() != null) {
                areStyleImage.get()!!.insertImage(s, AreImageSpan.ImageType.URL)
            }
        }
    }
}