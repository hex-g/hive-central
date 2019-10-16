package hex.hivecentral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var usernameField: EditText
    lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameField = findViewById<EditText>(R.id.mainActivity_editText_usernameField)
        passwordField = findViewById<EditText>(R.id.mainActivity_editText_passwordField)
    }

    fun submit(view: View) {
        val username = usernameField.text.toString()
        val password = passwordField.text.toString()

        if (authenticate(username, password)) {
            (application as Application).currentUser = username
            startActivity(Intent(this, Home::class.java))
        } else {
            passwordField.text.clear()
            Toast.makeText(this, R.string.mainActivity_toast_wrongCredentials, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        return username == "user" && password == "1234"
    }
}
