package tm.lab.w6sensory

import android.hardware.Sensor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tm.lab.w6sensory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sensory: Sensory
    private lateinit var listaSensorow : List<Sensor>
    private var tryb : Int = Sensor.TYPE_ALL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicjacja()

        binding.typyRG.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.allRB -> tryb = Sensor.TYPE_ALL
                R.id.proximityRB -> tryb = Sensor.TYPE_PROXIMITY
                R.id.lightRB -> tryb = Sensor.TYPE_LIGHT
                R.id.accRB -> tryb = Sensor.TYPE_ACCELEROMETER
                else ->  tryb = Sensor.TYPE_ALL
            }
        }

        binding.zaladowanieSensorowButton.setOnClickListener {
            listaSensorow = sensory.listaSensorow(tryb)
            binding.listaSensorow.text = ""
            for (item: Sensor in listaSensorow) {
                val a = "${binding.listaSensorow.text}${item.name}\n"
                binding.listaSensorow.text =  a
            }
        }
        binding.czysczenieListySensorowButton.setOnClickListener {
            binding.listaSensorow.text = getString(R.string.napis_pusta_lista)
        }
    }

    private fun inicjacja() {
        sensory = Sensory(applicationContext)
        binding.listaSensorow.text = ""
        binding.listaSensorow.text = getString(R.string.napis_pusta_lista)
     //   getString(R.string.napis_pusta_lista).also { binding.listaSensorow.text = it }

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }
}