package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private ListView listViewQuestions;
    private QuestionsListAdapter questionsListAdapter;

    private final View rootView;

    private final List<Listener> listeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        rootView = layoutInflater.inflate(R.layout.layout_questions_list, parent, false);

        listViewQuestions = findViewById(R.id.lst_questions);
        questionsListAdapter = new QuestionsListAdapter(getContext(), this);
        listViewQuestions.setAdapter(questionsListAdapter);
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
    public void onQuestionClicked(Question question) {
        for (Listener listener : listeners) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        questionsListAdapter.clear();
        questionsListAdapter.addAll(questions);
        questionsListAdapter.notifyDataSetChanged();
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }


}
