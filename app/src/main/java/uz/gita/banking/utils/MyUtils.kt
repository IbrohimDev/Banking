package uz.gita.banking.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import uz.gita.banking.data.enum.PinStatus
import uz.gita.banking.data.enum.StartEnum
import java.io.File


fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}


fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun String.startScreen(): StartEnum {
    return if (this == StartEnum.LOGIN.name) StartEnum.LOGIN
    else StartEnum.MAIN
}

fun String.pinScreen(): PinStatus {
   return  if(this == PinStatus.WithPin.name) PinStatus.WithPin
    else PinStatus.WithoutPin
}

fun File.toRequestData(): MultipartBody.Part {
    val requestFile = this.asRequestBody("image/jpeg".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("avatar", name, requestFile)
}
fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}
fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}
fun View.getString(stringResId: Int): String = resources.getString(stringResId)
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }
fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }