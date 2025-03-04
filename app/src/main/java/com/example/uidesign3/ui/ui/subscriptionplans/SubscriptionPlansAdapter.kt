import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ItemSubscriptionPlansBinding
import com.example.uidesign3.ui.ui.subscriptionplans.SubscriptionPlans

class SubscriptionPlansAdapter(
    private var plans: List<SubscriptionPlans>,
    private val onPlanSelected: (SubscriptionPlans) -> Unit
) : RecyclerView.Adapter<SubscriptionPlansAdapter.SubscriptionPlansViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionPlansViewHolder {
        val binding = ItemSubscriptionPlansBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SubscriptionPlansViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionPlansViewHolder, position: Int) {
        holder.bind(plans[position], position)
    }

    override fun getItemCount(): Int = plans.size

    inner class SubscriptionPlansViewHolder(
        private val binding: ItemSubscriptionPlansBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: SubscriptionPlans, position: Int) {
            binding.apply {
                val isSelected = position == selectedPosition
                radioButton.isChecked = isSelected

                // Set background based on selection
                root.findViewById<ConstraintLayout>(R.id.ConstraintLayout).setBackgroundResource(
                    if (isSelected) R.drawable.vector_rv_selector
                    else R.drawable.selector_subscription_plans
                )

                if (plan.planTime == "Yearly") {
                    root.foreground = AppCompatResources.getDrawable(root.context, R.drawable.ic_suggested_plan)
                    root.foregroundGravity = GravityCompat.END or Gravity.TOP
                } else {
                    root.foreground = null
                }

                planTime.text = plan.planTime
                planPrice.text = plan.planPrice
                planRechargeTime.text = plan.planDuration

                // Hide discount TextView if planDescription is null or empty
                if (plan.planDescription.isNullOrEmpty()) {
                    planDiscount.visibility = CardView.GONE
                } else {
                    planDiscount.visibility = CardView.VISIBLE
                    planDiscount.text = plan.planDescription
                }

                root.setOnClickListener {
                    val previousSelected = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(previousSelected)
                    notifyItemChanged(selectedPosition)
                    onPlanSelected(plan)
                }
            }
        }

    }
}
