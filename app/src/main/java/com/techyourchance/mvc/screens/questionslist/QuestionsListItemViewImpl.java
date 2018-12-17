package com.techyourchance.mvc.screens.questionslist;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListItemViewImpl implements QuestionsListItemView {

    private final View rootView;
    private final TextView tvTitle;

    private final List<Listener> listeners = new ArrayList<>(1);
    private Question question;

    public QuestionsListItemViewImpl(LayoutInflater layoutInflater, @Nullable ViewGroup parent) {

        rootView = layoutInflater.inflate(R.layout.layout_question_list_item, parent, false);
        tvTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener : listeners) {
                    listener.onQuestionClicked(question);
                }
            }
        });
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void bindQuestion(Question question) {
        this.question = question;
        tvTitle.setText(question.getTitle());
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }
}

