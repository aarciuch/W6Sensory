package tm.lab.w6sensory

import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tm.lab.w6sensory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

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
            zaladowanieListy(tryb)
        }

        binding.zaladowanieSensorowButton.setOnClickListener {

        }
        binding.czysczenieListySensorowButton.setOnClickListener {
            binding.listaSensorow.text = getString(R.string.napis_pusta_lista)
        }

        zaladowanieListy(Sensor.TYPE_ALL)
        binding.invalidateAll()
    }

    private fun inicjacja() {
        sensory = Sensory(applicationContext, binding)
        binding.sensory = this.sensory
        binding.listaSensorow.text = ""
        binding.listaSensorow.text = getString(R.string.napis_pusta_lista)
     //   getString(R.string.napis_pusta_lista).also { binding.listaSensorow.text = it }

    }


    private fun zaladowanieListy(tryb: Int) {
        listaSensorow = sensory.listaSensorow(tryb)
        binding.listaSensorow.text = ""
        for (item: Sensor in listaSensorow) {
            val a = "${binding.listaSensorow.text}${item.name}\n"
            binding.listaSensorow.text =  a
        }
    }

    override fun onStart() {
        super.onStart()
        if (sensory.proximitySensor != null) {
            sensory.sensorManager.registerListener(sensory, sensory.proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (sensory.lightSensor != null) {
            sensory.sensorManager.registerListener(sensory, sensory.lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (sensory.acc != null) {
            sensory.sensorManager.registerListener(sensory, sensory.acc,
                SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onStop() {
        super.onStop()
        sensory.sensorManager.unregisterListener(sensory)
    }
}