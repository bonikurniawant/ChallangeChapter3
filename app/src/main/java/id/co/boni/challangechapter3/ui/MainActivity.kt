package id.co.boni.challangechapter3.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.co.boni.challangechapter3.R
import id.co.boni.challangechapter3.controller.Callback
import id.co.boni.challangechapter3.controller.Controller
import id.co.boni.challangechapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Callback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = Controller(this)

        val player = arrayListOf(
            binding.ivBatu,
            binding.ivKertas,
            binding.ivGunting,
        )

        val com = arrayListOf(
            binding.ivBatuCom,
            binding.ivKertasCom,
            binding.ivGuntingCom,
        )

        player.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                imageView.selected()
                val comSelect = com.random()
                controller.banding(
                    imageView.contentDescription.toString(),
                    comSelect.contentDescription.toString()
                )

                comSelect.selected()
                Log.d("Pemain memilih ", imageView.contentDescription.toString())
                Log.d("Computer memilih ", comSelect.contentDescription.toString())

                Toast.makeText (this, "Tekan tombol refresh untuk bermain kembali", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivRefresh.setOnClickListener {

            player.forEach {
                it.unselected()
            }

            com.forEach {
                it.unselected()
            }

            binding.tvResult.setText("VS")
            binding.tvResult.setTextColor(Color.parseColor("#FF0000"))
            binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80F)
            binding.tvResult.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            isSelectEnable(enable = true)
            Log.d("Tombol refresh ditekan", binding.ivRefresh.contentDescription.toString())
        }
    }

    fun isSelectEnable(enable: Boolean) {

        binding.ivBatu.isEnabled = enable
        binding.ivKertas.isEnabled = enable
        binding.ivGunting.isEnabled = enable
    }

    fun ImageView.selected() {
        setBackgroundResource(R.drawable.bg_rounded)
        backgroundTintList =
            ContextCompat.getColorStateList(this@MainActivity, R.color.blueSelector)
    }

    fun ImageView.unselected() {
        setBackgroundResource(R.drawable.bg_rounded)
        backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.white)
    }

    override fun tampilkanHasil(result: String) {
        binding.tvResult.text = result
        binding.tvResult.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30F)
        binding.tvResult.setTextColor(Color.parseColor("#FFFFFFFF"))
        isSelectEnable(false)
        if (result == "DRAW") {
            binding.tvResult.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_dark))
        }
        Log.d("Hasil ", result)
        return
    }
}
