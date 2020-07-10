package hr.johndoeveloper.utils.sensors.gyroscope

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import hr.johndoeveloper.sportato.app.Sportato
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.*

@Singleton
class Gyroscope @Inject constructor() : SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    @ExperimentalCoroutinesApi
    val stateFlowSensorValues = MutableStateFlow(floatArrayOf())
    @ExperimentalCoroutinesApi
    val stateFlowSensorAccuracy = MutableStateFlow(0)

    fun registerSensor() {
        sensorManager = Sportato.getAppContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    @ExperimentalCoroutinesApi
    override fun onAccuracyChanged(p0: Sensor?, accuracy: Int) {
        stateFlowSensorAccuracy.value = accuracy
    }

    @ExperimentalCoroutinesApi
    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.values?.let {
            stateFlowSensorValues.value = it
        }
    }
}