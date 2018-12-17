package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

public class QuestionsListAdapter extends ArrayAdapter<Question> implements QuestionsListItemView.Listener {

    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }

    private final OnQuestionClickListener onQuestionClickListener;

    public QuestionsListAdapter(Context context,
                                OnQuestionClickListener onQuestionClickListener) {
        super(context, 0);
        this.onQuestionClickListener = onQuestionClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            QuestionsListItemView questionsListItemView = new QuestionsListItemViewImpl(LayoutInflater.from(getContext()), parent);
            questionsListItemView.registerListener(this);
            convertView = questionsListItemView.getRootView();
            convertView.setTag(questionsListItemView);
        }

        final Question question = getItem(position);

        QuestionsListItemView questionsListItemView = (QuestionsListItemView) convertView.getTag();
        questionsListItemView.bindQuestion(question);

        return convertView;
    }

    @Override
    public void onQuestionClicked(Question question) {
        onQuestionClickListener.onQuestionClicked(question);
    }
}
