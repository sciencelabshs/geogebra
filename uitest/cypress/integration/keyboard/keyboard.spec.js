import '../../support/embed/commands.js'
import {selectors} from '@geogebra/web-test-harness/selectors'

describe('Keyboard ANS button test', () => {
    beforeEach(() => {
        cy.visit('graphing.html');
        cy.window().then((win) => {
             win.PointerEvent = null;
        });
        cy.get("body.application");
    });

    afterEach(cy.setSaved);

    it("should show 'ans' button in the Algebra View", () => {
        selectors.mathKeyboardWithAns.get().should("be.visible");
        cy.get('div[aria-label="ans"]').should("be.visible");
    });
    it("shows the 'ans' button after switching keyboards", () => {
        selectors.functionsKeyboard.get().click();
        selectors.mathKeyboardWithAns.get().click();
        cy.get('div[aria-label="ans"]').should("be.visible");
    });
    it("inserts the result of the previous cell when the 'ans' button is pressed", () => {
        cy.writeInAVInput("1+3 {enter}");
        cy.get('div[aria-label="ans"]').click();
        cy.writeInAVInput("+ 2 {enter}");
        cy.get(".avValue").should("contain", "6");
    });
    it("does not show the 'ans' button in the properties view", () => {
        selectors.graphicsViewContextMenu.get().click();
        cy.get('.gwt-MenuItem').contains('Settings').click();
        cy.get('.gwt-SuggestBox').first().click();
        cy.get('div[aria-label="ans"]').should("not.be.visible");
    });

    it("does not show the 'ans' button in for symbolic inputbox", () => {
        cy.writeInAVInput("f(x)=x{enter}");
        cy.writeInAVInput("InputBox(f){enter}");
        selectors.euclidianView.get().trigger('mousedown', 100, 100);
        selectors.euclidianView.get().trigger('mousedown', 140, 50).trigger('mouseup',  140, 50);

        cy.keyboardShouldPresent();
        cy.get('div[aria-label="ans"]').should("not.be.visible");
    });
})