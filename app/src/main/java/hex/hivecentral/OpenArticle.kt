package hex.hivecentral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_open_article.*
import org.json.JSONObject

class OpenArticle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_article)

        open_article_title.text = intent.getStringExtra("path")

        var content = ""
        val contentJson = JSONObject(intent.getStringExtra("content") ?: "{\"blocks\": []}")
        val blocks = contentJson.getJSONArray("blocks")
        val len = blocks.length()
        for (i in 0 until len) {
            val block = blocks.getJSONObject(i)
            if (block.getString("type") == "paragraph") {
                content += block.getJSONObject("data").getString("text") + "\n\n"
            }
        }

        var readIn = content.length/200

        tv_ReadIn.text = "Leia em "+readIn+" min"

        open_article_article.text = content
    }
}
