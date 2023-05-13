import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi_latihan.databinding.ItemSourceBinding
import com.example.newsapi_latihan.model.Source

class SourceAdapter(var listSource : List<Source>): RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    var onClick : ((Source) -> Unit)? = null

    class ViewHolder(var binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.ViewHolder {
        var view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var source = listSource.get(position)
        if (source != null){
            holder.binding.nameSource.text = source.name
            holder.binding.itemSource.setOnClickListener {
                onClick?.invoke(listSource[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listSource.size
    }
}