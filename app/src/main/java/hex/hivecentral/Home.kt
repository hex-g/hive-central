package hex.hivecentral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Home : AppCompatActivity() {
    var lastTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if ((application as Application).currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<TextView>(R.id.home_textView_welcomeText).text =
            getString(R.string.home_textView_welcomeText, (application as Application).currentUser)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime <= 2000L) {
            finishAffinity()
        } else {
            Toast.makeText(this, getString(R.string.home_toast_backpressed), Toast.LENGTH_SHORT).show()
            lastTime = currentTime
        }
    }

    fun logout(view: View) {
        (application as Application).currentUser = null
        finish()
    }
}
