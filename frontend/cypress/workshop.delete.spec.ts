describe('Workshop Delete Page', () => {
    beforeEach(() => {
        cy.visit('/workshops/delete/1');
    });

    it('should display the workshop delete confirmation', () => {
        cy.get('input[name="titulo"]', { timeout: 10000 }).should('be.visible').and('have.value', 'Workshop Title');
        cy.get('input[name="descricao"]', { timeout: 10000 }).should('be.visible').and('have.value', 'Workshop Description');
    });

    it('should delete workshop successfully', () => {
        cy.get('button[color="warn"]').click();
        cy.url().should('include', '/workshops');
        cy.contains('Workshop excluído com sucesso').should('be.visible');
    });

    it('should cancel deletion and navigate back', () => {
        cy.get('button').contains('Cancelar').click();
        cy.url().should('include', '/workshops');
    });
});