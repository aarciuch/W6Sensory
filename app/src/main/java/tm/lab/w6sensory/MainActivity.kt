package tm.lab.w6sensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tm.lab.w6sensory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var listaSensorow : List<Sensor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicjacja()
        binding.zaladowanieSensorowButton.setOnClickListener {
            listaSensorow = sensorManager.getSensorList(Sensor.TYPE_ALL)
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
        binding.listaSensorow.text = ""
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
}