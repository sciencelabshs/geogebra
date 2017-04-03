package org.geogebra.keyboard.base.linear.impl.factory;

import org.geogebra.keyboard.base.Action;
import org.geogebra.keyboard.base.Resource;
import org.geogebra.keyboard.base.linear.LinearKeyboard;
import org.geogebra.keyboard.base.linear.impl.LinearKeyboardImpl;
import org.geogebra.keyboard.base.linear.impl.RowImpl;

import static org.geogebra.keyboard.base.ButtonConstants.DEGREE;
import static org.geogebra.keyboard.base.ButtonConstants.EULER;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addConstantCustomButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addConstantInputButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addConstantInputCommandButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addInputButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addTranslateInputCommandButton;

class FunctionKeyboardFactory {

    LinearKeyboard createFunctionKeyboard(ButtonFactory buttonFactory) {
        LinearKeyboardImpl functionKeyboard = new LinearKeyboardImpl();
        float width = 5.0f / 3;
        RowImpl row = functionKeyboard.nextRow(9.2f);
        addTranslateInputCommandButton(row, buttonFactory, "Function.sin", "Function.sin", width);
        addTranslateInputCommandButton(row, buttonFactory, "Function.cos", "Function.cos", width);
        addTranslateInputCommandButton(row, buttonFactory, "Function.tan", "Function.tan", width);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "%");
        addInputButton(row, buttonFactory, "!");
        addInputButton(row, buttonFactory, "$");
        addInputButton(row, buttonFactory, DEGREE);

        row = functionKeyboard.nextRow(9.2f);
        addTranslateInputCommandButton(row, buttonFactory, "Function.asin", "Function.asin", width);
        addTranslateInputCommandButton(row, buttonFactory, "Function.acos", "Function.acos", width);
        addTranslateInputCommandButton(row, buttonFactory, "Function.atan", "Function.atan", width);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "{");
        addInputButton(row, buttonFactory, "}");
        addInputButton(row, buttonFactory, ";");
        addInputButton(row, buttonFactory, ":=", "\u2254");

        row = functionKeyboard.nextRow(9.2f);
        addInputButton(row, buttonFactory, "ln", width);
        addConstantInputButton(row, buttonFactory, Resource.LOG_10, "log_{10}", width);
        addConstantInputButton(row, buttonFactory, Resource.LOG_B, "logb", width);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addConstantInputCommandButton(row, buttonFactory, Resource.LOG_10, "Derivative", 1.0f);
        addConstantInputCommandButton(row, buttonFactory, Resource.INTEGRAL, "Integral", 1.0f);
        addInputButton(row, buttonFactory, "i", "\u03af");
        addConstantCustomButton(row, buttonFactory, Resource.BACKSPACE_DELETE, Action.BACKSPACE_DELETE);

        row = functionKeyboard.nextRow(9.2f);
        addConstantInputButton(row, buttonFactory, Resource.POWE_X, EULER + "^", width);
        addConstantInputButton(row, buttonFactory, Resource.POW10_X, "10^", width);
        addConstantInputButton(row, buttonFactory, Resource.N_ROOT, "nroot", width);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addConstantInputButton(row, buttonFactory, Resource.A_N, "a_n");
        addConstantCustomButton(row, buttonFactory, Resource.LEFT_ARROW, Action.LEFT_CURSOR);
        addConstantCustomButton(row, buttonFactory, Resource.RIGHT_ARROW, Action.RIGHT_CURSOR);
        addConstantCustomButton(row, buttonFactory, Resource.RETURN_ENTER, Action.RETURN_ENTER);

        return functionKeyboard;
    }
}
