import androidx.annotation.WorkerThread
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.data.entities.Device

class DeviceRepository (private val deviceDao: DeviceDatabaseDao){

    @WorkerThread
    suspend fun insert(device: Device){
        deviceDao.insert(device)
    }

    val deviceList = deviceDao.getDevice()
}