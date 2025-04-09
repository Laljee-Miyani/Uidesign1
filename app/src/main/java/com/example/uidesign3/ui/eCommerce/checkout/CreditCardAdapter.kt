import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.databinding.ItemCreditCardBinding
import com.example.uidesign3.ui.eCommerce.checkout.CardDetailsAdapter
import com.example.uidesign3.ui.eCommerce.checkout.CardItem

class CreditCardAdapter(
    private val onCardSelected: (Int, Int) -> Unit
) : RecyclerView.Adapter<CreditCardAdapter.ViewHolder>() {

    private var selectedTypePosition: Int = -1
    private var selectedCardPosition: Int = -1
    private var expandedTypePosition: Int = -1

    private val creditCardTypes = listOf(
        "Credit Card" to listOf(
            CardItem(1, "Mastercard", "xxxx xxxx xxxx 1234"),
            CardItem(2, "Visa", "xxxx xxxx xxxx 5678")
        ),
        "Apple Pay" to listOf(
            CardItem(3, "Visa Debit", "xxxx xxxx xxxx 9012"),
            CardItem(4, "Mastercard Debit", "xxxx xxxx xxxx 3456")
        )
    )

    class ViewHolder(val binding: ItemCreditCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cardRecyclerView = binding.recyclerView

        fun bind(cardType: String, isExpanded: Boolean) {
            binding.paymentType.text = cardType
            binding.radioButton.isChecked = isExpanded

            val visibility = if (isExpanded) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = visibility
            binding.addCardLayout.visibility = visibility
            binding.checkbox.visibility = visibility
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            ItemCreditCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.binding.radioButton.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                expandedTypePosition = if (expandedTypePosition == position) -1 else position
                notifyDataSetChanged()
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (cardType, cards) = creditCardTypes[position]
        val isExpanded = position == expandedTypePosition
        holder.bind(cardType, isExpanded)

        if (isExpanded) {
            val updatedCards = cards.mapIndexed { index, card ->
                card.copy(isSelected = position == selectedTypePosition && index == selectedCardPosition)
            }

            holder.cardRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CardDetailsAdapter { cardPosition ->
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        selectedTypePosition = adapterPosition
                        selectedCardPosition = cardPosition

                        notifyDataSetChanged()

                        onCardSelected(adapterPosition, cardPosition)
                    }
                }.apply {
                    submitList(updatedCards)
                }
            }
        }
    }

    override fun getItemCount() = creditCardTypes.size
}