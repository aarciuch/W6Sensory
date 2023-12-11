package tm.lab.w6sensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import tm.lab.w6sensory.databinding.ActivityMainBinding

/**
 * @brief klasa do obsługi sensorów
 */
class Sensory (private val context:Context, val binding: ActivityMainBinding) : SensorEventListener {

    /**
     * \brief instacja sensor managera
     */
    private var _sensorManager: SensorManager
    public val sensorManager : SensorManager
        get() = _sensorManager

    private var _proximitySensor: Sensor
    public val proximitySensor : Sensor
        get() = _proximitySensor

    private var _lightSensor: Sensor
    public val lightSensor : Sensor
        get() = _lightSensor

    private var _acc : Sensor
    public val acc : Sensor
        get() = _acc

    private var _proximityWartosc : Float = 0.0F
    public val proximityWartosc : Float
        get() = _proximityWartosc

    private var _lightWartosc : Float = 0.0F
    public val lightWartosc : Float
        get() = _lightWartosc


    private var _accWartosc = floatArrayOf(0F)
    public val accWartosc : FloatArray
        get() = _accWartosc

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

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                _proximityWartosc = event.values[0]
            }
            if (event.sensor.type == Sensor.TYPE_LIGHT) {
                _lightWartosc = event.values[0]
            }
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                _accWartosc = event.values
            }
        }
        binding.invalidateAll()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}