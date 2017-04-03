package org.geogebra.keyboard.base.linear.impl.factory;

import org.geogebra.keyboard.base.Action;
import org.geogebra.keyboard.base.Resource;
import org.geogebra.keyboard.base.linear.LinearKeyboard;
import org.geogebra.keyboard.base.linear.impl.LinearKeyboardImpl;
import org.geogebra.keyboard.base.linear.impl.RowImpl;

import static org.geogebra.keyboard.base.ButtonConstants.DIVISION;
import static org.geogebra.keyboard.base.ButtonConstants.EULER;
import static org.geogebra.keyboard.base.ButtonConstants.GEQ;
import static org.geogebra.keyboard.base.ButtonConstants.LEQ;
import static org.geogebra.keyboard.base.ButtonConstants.MULTIPLICATION;
import static org.geogebra.keyboard.base.ButtonConstants.PI;
import static org.geogebra.keyboard.base.ButtonConstants.ROOT;
import static org.geogebra.keyboard.base.ButtonConstants.SUP2;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addConstantCustomButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addConstantInputButton;
import static org.geogebra.keyboard.base.linear.impl.factory.Util.addInputButton;

class MathKeyboardFactory {

    LinearKeyboard createMathKeyboard(ButtonFactory buttonFactory) {
        LinearKeyboardImpl mathKeyboard = new LinearKeyboardImpl();

        RowImpl row = mathKeyboard.nextRow(9.2f);
        addInputButton(row, buttonFactory, "x");
        addInputButton(row, buttonFactory, "y");
        addInputButton(row, buttonFactory, "z");
        addInputButton(row, buttonFactory, PI);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "7");
        addInputButton(row, buttonFactory, "8");
        addInputButton(row, buttonFactory, "9");
        addInputButton(row, buttonFactory, MULTIPLICATION, "*");
        addInputButton(row, buttonFactory, DIVISION, "/");

        row = mathKeyboard.nextRow(9.2f);
        addConstantInputButton(row, buttonFactory, Resource.POWA2, SUP2);
        addConstantInputButton(row, buttonFactory, Resource.POWAB, "^");
        addConstantInputButton(row, buttonFactory, Resource.ROOT, ROOT);
        addInputButton(row, buttonFactory, EULER);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "4");
        addInputButton(row, buttonFactory, "5");
        addInputButton(row, buttonFactory, "6");
        addInputButton(row, buttonFactory, "+");
        addInputButton(row, buttonFactory, "-");

        row = mathKeyboard.nextRow(9.2f);
        addInputButton(row, buttonFactory, "<");
        addInputButton(row, buttonFactory, ">");
        addInputButton(row, buttonFactory, LEQ);
        addInputButton(row, buttonFactory, GEQ);
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "1");
        addInputButton(row, buttonFactory, "2");
        addInputButton(row, buttonFactory, "3");
        addInputButton(row, buttonFactory, "=");
        addConstantCustomButton(row, buttonFactory, Resource.BACKSPACE_DELETE, Action.BACKSPACE_DELETE);

        row = mathKeyboard.nextRow(9.2f);
        addInputButton(row, buttonFactory, "(");
        addInputButton(row, buttonFactory, ")");
        addInputButton(row, buttonFactory, "|a|", "|");
        addInputButton(row, buttonFactory, ",");
        addButton(row, buttonFactory.createEmptySpace(0.2f));
        addInputButton(row, buttonFactory, "0");
        addInputButton(row, buttonFactory, ".");
        addConstantCustomButton(row, buttonFactory, Resource.LEFT_ARROW, Action.LEFT_CURSOR);
        addConstantCustomButton(row, buttonFactory, Resource.RIGHT_ARROW, Action.RIGHT_CURSOR);
        addConstantCustomButton(row, buttonFactory, Resource.RETURN_ENTER, Action.RETURN_ENTER);

        return mathKeyboard;
    }
}
