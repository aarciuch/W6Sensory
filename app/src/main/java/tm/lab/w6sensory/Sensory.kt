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


    private var _proximitySensor: Sensor
    private var _lightSensor: Sensor
    private var _acc : Sensor

    /**
     * @brief konstruktor
     */
    init {
        _sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        _proximitySensor = _sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) as Sensor
        _lightSensor = _sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) as Sensor
        _acc = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) as Sensor
    }

    /**
     * @brief zwaraca liste sensorów określonego typu
     */
    public fun listaSensorow(typ : Int) : List<Sensor> {
            return _sensorManager.getSensorList(typ)
    }

    public fun rejestrowanie(typ: Int) {

    }

    public fun wyrejestrowanie()  {
        //mSensorManager.unregisterListener(this);
    }
}