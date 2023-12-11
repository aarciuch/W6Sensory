package tm.lab.w6sensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

/**
 * @brief klasa do obsługi sensorów
 */
class Sensory (private val context:Context) {

    /**
     * \brief instacja sensor managera
     */
    private var _sensorManager: SensorManager
    public val sensorManager : SensorManager
        get() = _sensorManager

    /**
     * @brief konstruktor
     */
    init {
        _sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    /**
     * @brief zwaraca liste sensorów określonego typu
     */
    public fun listaSensorow(typ : Int) : List<Sensor> {
        when (typ) {
            Sensor.TYPE_ALL -> return _sensorManager.getSensorList(Sensor.TYPE_ALL)
            else -> return  _sensorManager.getSensorList(Sensor.TYPE_ALL)
        }
    }

}