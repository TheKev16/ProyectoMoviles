package com.kevin.proyectomovileskch.ui.doctor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.data.model.Doctor

class DoctorAdapter(context: Context, private val doctors: List<Doctor>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = doctors.size
    override fun getItem(position: Int): Doctor? = doctors.getOrNull(position)
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.item_doctor, parent, false)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvSpecialty = view.findViewById<TextView>(R.id.tvSpecialty)
        val btnMore = view.findViewById<ImageButton>(R.id.btnMore)

        val doctor = doctors[position]
        tvName.text = parent?.context?.getString(R.string.person_name_format, doctor.firstName, doctor.lastName)
        tvSpecialty.text = doctor.specialty

        btnMore.setOnClickListener {
            // Popup menu with options
            val popup = PopupMenu(parent?.context, it)
            popup.menu.add(0, 0, 0, parent?.context?.getString(R.string.contact_send_message))
            popup.menu.add(0, 1, 1, parent?.context?.getString(R.string.contact_video_call))
            popup.menu.add(0, 2, 2, parent?.context?.getString(R.string.contact_call))
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    0 -> {
                        val i = android.content.Intent(parent?.context, com.kevin.proyectomovileskch.ui.message.MessageActivity::class.java)
                        i.putExtra("doctorId", doctor.id)
                        parent?.context?.startActivity(i)
                        true
                    }
                    1 -> {
                        Toast.makeText(parent?.context, "Video call prototype (not implemented)", Toast.LENGTH_SHORT).show()
                        true
                    }
                    2 -> {
                        val i = android.content.Intent(parent?.context, com.kevin.proyectomovileskch.ui.call.CallingActivity::class.java)
                        i.putExtra("doctorId", doctor.id)
                        parent?.context?.startActivity(i)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        return view
    }
}
