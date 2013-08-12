/*
 * Copyright 2013 Wastl Kraus <derdoktor667@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.derdoktor667.dev.thematrix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	private Context context;
	private String[] mTitle;
	private String[] mSubTitle;
	// private int[] mIcon;
	private LayoutInflater inflater;

	public MenuListAdapter(Context pContext, String[] pTitle,
			String[] pSubtitle, int[] pIcon) {
		context = pContext;
		mTitle = pTitle;
		mSubTitle = pSubtitle;
		// mIcon = pIcon;
	}

	@Override
	public int getCount() {
		return mTitle.length;
	}

	@Override
	public Object getItem(int position) {
		return mTitle[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.drawer_list_item, parent,
				false);

		TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
		TextView txtSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
		// ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

		txtTitle.setText(mTitle[position]);
		txtSubTitle.setText(mSubTitle[position]);
		// imgIcon.setImageResource(mIcon[position]);

		return itemView;
	}
}
