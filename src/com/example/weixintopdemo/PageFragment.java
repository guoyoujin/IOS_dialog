package com.example.weixintopdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weixintopdemo.widget.Dialog;
import com.example.weixintopdemo.widget.Dialog.DialogClickListener;
import com.example.weixintopdemo.widget.Dialog.DialogItemClickListener;

/**
 * 
 */
public class PageFragment extends Fragment {

	public static final String ARG = "arg";

	private TextView text;
	private int position;

	public static PageFragment newInstance(int position) {
		PageFragment pageFragment = new PageFragment();
		Bundle b = new Bundle();
		b.putInt(ARG, position);
		pageFragment.setArguments(b);
		return pageFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		position = getArguments().getInt(ARG, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_page, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		text = (TextView) getView().findViewById(R.id.text);
		text.setText(String.valueOf(position));
		getView().findViewById(R.id.s).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Dialog.showRadioDialog(getActivity(), "强制对话框", new DialogClickListener() {
					@Override
					public void confirm() {
						Toast.makeText(getActivity(), "确定了", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void cancel() {
					}
				});
			}
		});
		getView().findViewById(R.id.d).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Dialog.showSelectDialog(getActivity(), "选择对话框", new DialogClickListener() {
					@Override
					public void confirm() {
						Toast.makeText(getActivity(), "确定了", Toast.LENGTH_SHORT).show();
					}
					@Override
					public void cancel() {
						Toast.makeText(getActivity(), "被关闭了", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		getView().findViewById(R.id.f).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String[] item={"梁","冬","陈"};
				Dialog.showListDialog(getActivity(), "请选择您的姓名",item,new DialogItemClickListener() {
					@Override
					public void confirm(String result) {
						Toast.makeText(getActivity(),result, Toast.LENGTH_SHORT).show();
						
					}
				});
			}
		});
	}

}
