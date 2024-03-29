package com.stormphoenix.ogit.adapters

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.stormphoenix.ogit.entity.github.GitUser
import com.stormphoenix.ogit.R
import com.stormphoenix.ogit.adapters.GitUserAdapter.GitUserViewHolder
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter
import com.stormphoenix.ogit.utils.ImageUtils

import butterknife.BindView
import butterknife.ButterKnife
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by wanlei on 18-3-2.
 */

class GitUserAdapter(context: Context, dataList: MutableList<GitUser>) : BaseRecyclerAdapter<GitUser, GitUserViewHolder>(context, dataList) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GitUserViewHolder {
        val viewItem = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_git_owner, parent, false)
        return GitUserViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(data[position])
    }

    class GitUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mHeaderImage: CircleImageView? = null
        internal var mTextUserName: TextView? = null

        init {
            mHeaderImage = itemView.findViewById(R.id.header_image) as CircleImageView?
            mTextUserName = itemView.findViewById(R.id.text_user_name) as TextView?
        }

        fun bind(model: GitUser) {
            var options: DisplayImageOptions? = null
            options = DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                    .considerExifParams(true).build()
            ImageUtils.getInstance().displayImage(model.avatarUrl, mHeaderImage, options)
            mTextUserName!!.text = model.login
        }
    }
}
