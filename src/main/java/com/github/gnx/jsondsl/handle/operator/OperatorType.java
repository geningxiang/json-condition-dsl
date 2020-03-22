package com.github.gnx.jsondsl.handle.operator;

public enum OperatorType {
    GREATER_THAN {
        @Override
        public boolean math(int compare) {
            return compare > 0;
        }
    },
    GREATER_THAN_OR_EQUAL {
        @Override
        public boolean math(int compare) {
            return compare >= 0;
        }
    },
    LESS_THAN {
        @Override
        public boolean math(int compare) {
            return compare < 0;
        }
    },
    LESS_THAN_OR_EQUAL {
        @Override
        public boolean math(int compare) {
            return compare <= 0;
        }
    },
    EQUAL {
        @Override
        public boolean math(int compare) {
            return compare == 0;
        }
    },
    NOT_EQUAL {
        @Override
        public boolean math(int compare) {
            return compare != 0;
        }
    };


    public abstract boolean math(int compare);

}