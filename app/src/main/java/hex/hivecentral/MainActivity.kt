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

        usernameField = findViewById(R.id.mainActivity_editText_usernameField)
        passwordField = findViewById(R.id.mainActivity_editText_passwordField)
    }

    fun submit(view: View) {
        val username = usernameField.text.toString()
        val password = passwordField.text.toString()

        passwordField.text.clear()
        if (authenticate(username, password)) {
            usernameField.text.clear()
            (application as Application).currentUser = username
            startActivity(Intent(this, Home::class.java))
        } else {
            Toast.makeText(this, R.string.mainActivity_toast_wrongCredentials, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        val validUser = username == "user";
        val validPassword = password == "1234"
        setValidationIcons(validUser,validPassword)
        return validUser && validPassword
    }

    fun setValidationIcons(isValidUser:Boolean, isValidPassword: Boolean){
        usernameField.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_red_exclamation_icon,0)
        passwordField.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_red_exclamation_icon,0)
        if(isValidUser){
            usernameField.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_tick_inside_circle,0)
            if(isValidPassword){
                passwordField.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_tick_inside_circle,0)
            }
        }
    }
}
