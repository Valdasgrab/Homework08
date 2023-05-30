package lt.vgrabauskas.homework08.repository

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime


@Parcelize
data class Note(
    private val _id: Int,
    private var _name: String,
    private var _details: String
) : Parcelable {
    @IgnoredOnParcel
    var id = this._id
        private set

    @IgnoredOnParcel
    var name: String = ""
        get():String {
            return _name
        }
        set(value) {
            field = value
            this._name = value
        }

    @IgnoredOnParcel
    var details: String
        get():String {
            return _details
        }
        set(value) {
            this._details = value
        }
}