package gm.books.app.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.squareup.picasso.Picasso
import gm.books.app.R
import gm.books.app.databinding.BannerItemBinding
import gm.books.app.presentation.fragments.MainFragmentDirections
import gm.books.domain.entities.Banner


class BannerAdapter(private val banners: List<Banner>) :
    LoopingPagerAdapter<Banner>(banners, true) {

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView: ImageView = convertView.findViewById<View>(R.id.banner_image) as ImageView
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        convertView.setOnClickListener {
            val bookId = banners[listPosition].book_id
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(bookId)
            it.findNavController().navigate(action)
        }

        Picasso.get().load(banners[listPosition].cover).into(imageView)
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return BannerItemBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        ).root
    }
}