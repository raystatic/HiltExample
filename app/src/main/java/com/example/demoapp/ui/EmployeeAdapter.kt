package com.example.demoapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.models.Employee
import kotlinx.android.synthetic.main.employee_item.view.*

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(){

    inner class EmployeeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Employee>(){
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Employee>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.employee_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            tvName.text = "${item.employee_name}"
            tvSalary.text = "Salary: Rs.${item.employee_salary}"
            tvAge.text = "Age: ${item.employee_age}"
        }

    }
}