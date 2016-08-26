package ru.marten.model;

import org.json.JSONObject;

/**
 * Created by marten on 21.08.16.
 */
public class QuestModel extends JSONObject {

    private static final String RIGHT_ANSWER = "right_answer";
    private static final String SECOND_VARIANT = "second_variant";
    private static final String THIRD_VARIANT = "third_variant";

    public QuestModel(String right_answer, String second_variant, String third_variant) {
            super();
    }


}
