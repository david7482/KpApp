package com.david74.kpapp.util.preference.ui;

import com.david74.kpapp.util.preference.BasePreferenceKey;

public enum UiPreferenceKey implements BasePreferenceKey {

    TEST_A {
        @Override
        public String getKey() {
            return "test_a";
        }

        @Override
        public Class getType() {
            return String.class;
        }

        @Override
        public Object getDefault() {
            return "";
        }
    },
    TEST_B {
        @Override
        public String getKey() {
            return "test_b";
        }

        @Override
        public Class getType() {
            return Boolean.class;
        }

        @Override
        public Object getDefault() {
            return false;
        }
    }
}
