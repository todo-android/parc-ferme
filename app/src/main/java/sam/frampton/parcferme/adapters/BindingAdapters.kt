package sam.frampton.parcferme.adapters

import android.text.TextPaint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import sam.frampton.parcferme.R
import sam.frampton.parcferme.data.Constructor
import sam.frampton.parcferme.data.Driver
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private val countryFlags = mapOf(
    "Australia" to R.drawable.ic_au,
    "Austria" to R.drawable.ic_at,
    "Azerbaijan" to R.drawable.ic_az,
    "Bahrain" to R.drawable.ic_bh,
    "Belgium" to R.drawable.ic_be,
    "Brazil" to R.drawable.ic_br,
    "Canada" to R.drawable.ic_ca,
    "China" to R.drawable.ic_cn,
    "France" to R.drawable.ic_fr,
    "Germany" to R.drawable.ic_de,
    "Hungary" to R.drawable.ic_hu,
    "India" to R.drawable.ic_in,
    "Italy" to R.drawable.ic_it,
    "Japan" to R.drawable.ic_jp,
    "Korea" to R.drawable.ic_kr,
    "Malaysia" to R.drawable.ic_my,
    "Mexico" to R.drawable.ic_mx,
    "Monaco" to R.drawable.ic_mc,
    "Netherlands" to R.drawable.ic_nl,
    "Portugal" to R.drawable.ic_pt,
    "Russia" to R.drawable.ic_ru,
    "Saudi Arabia" to R.drawable.ic_sa,
    "Singapore" to R.drawable.ic_sg,
    "Spain" to R.drawable.ic_es,
    "Turkey" to R.drawable.ic_tr,
    "UAE" to R.drawable.ic_ae,
    "UK" to R.drawable.ic_gb,
    "USA" to R.drawable.ic_us
)

private val nationalityFlags = mapOf(
    "American" to R.drawable.ic_us,
    "Australian" to R.drawable.ic_au,
    "Austrian" to R.drawable.ic_at,
    "Belgian" to R.drawable.ic_be,
    "Brazilian" to R.drawable.ic_br,
    "British" to R.drawable.ic_gb,
    "Canadian" to R.drawable.ic_ca,
    "Danish" to R.drawable.ic_dk,
    "Dutch" to R.drawable.ic_nl,
    "Finnish" to R.drawable.ic_fi,
    "French" to R.drawable.ic_fr,
    "German" to R.drawable.ic_de,
    "Indian" to R.drawable.ic_in,
    "Indonesian" to R.drawable.ic_id,
    "Italian" to R.drawable.ic_it,
    "Japanese" to R.drawable.ic_jp,
    "Malaysian" to R.drawable.ic_my,
    "Mexican" to R.drawable.ic_mx,
    "Monegasque" to R.drawable.ic_mc,
    "New Zealander" to R.drawable.ic_nz,
    "Polish" to R.drawable.ic_pl,
    "Russian" to R.drawable.ic_ru,
    "Spanish" to R.drawable.ic_es,
    "Swedish" to R.drawable.ic_se,
    "Swiss" to R.drawable.ic_ch,
    "Thai" to R.drawable.ic_th,
    "Venezuelan" to R.drawable.ic_ve
)

@BindingAdapter("country")
fun ImageView.setCountry(country: String) {
    this.setImageResource(countryFlags[country] ?: R.drawable.ic_default_flag)
    this.contentDescription = country
}

@BindingAdapter("nationality")
fun ImageView.setNationality(nationality: String) {
    this.setImageResource(nationalityFlags[nationality] ?: R.drawable.ic_default_flag)
    this.contentDescription = nationality
}

@BindingAdapter("driverName")
fun TextView.setDriverName(driver: Driver) {
    this.text =
        this.context.getString(
            R.string.driver_full_name,
            driver.givenName,
            driver.familyName
        )
}

@BindingAdapter("driverNumber")
fun TextView.setDriverNumber(driver: Driver) {
    this.minWidth = TextPaint().let {
        it.textSize = this.textSize
        it.measureText("00").toInt()
    }
    this.visibility =
        driver.permanentNumber?.let {
            this.text = it.toString()
            View.VISIBLE
        } ?: View.GONE
}

@BindingAdapter("position")
fun TextView.setPosition(position: Int) {
    this.minWidth = TextPaint().let {
        it.textSize = this.textSize
        it.measureText("00").toInt()
    }
    this.text = position.toString()
}

@BindingAdapter("constructors")
fun TextView.setConstructors(constructors: List<Constructor>) {
    this.text = constructors.map(Constructor::name).joinToString()
}

@BindingAdapter("points")
fun TextView.setPoints(points: Double) {
    this.minWidth = TextPaint().let {
        it.textSize = this.textSize
        it.measureText("Points: 000").toInt()
    }
    this.text =
        this.context.getString(
            R.string.standing_points,
            if (points.rem(1) == 0.0) {
                (points.toInt())
            } else {
                points
            }.toString()
        )
}

@BindingAdapter("wins")
fun TextView.setWins(wins: Int) {
    this.text = this.context.getString(R.string.standing_wins, wins.toString())
}


@BindingAdapter("date")
fun TextView.setDate(date: LocalDate) {
    this.text = date.format(DateTimeFormatter.ofPattern("MMM dd"))
}