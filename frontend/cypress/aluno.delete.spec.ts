describe('Aluno Delete Page', () => {
    beforeEach(() => {
        cy.visit('/alunos/delete/1');
    });

    it('should display the aluno delete form', () => {
        cy.get('input[name="nome"]', { timeout: 10000 }).should('be.visible').and('have.value', 'Nome do Aluno');
        cy.get('input[name="email"]', { timeout: 10000 }).should('be.visible').and('have.value', 'email@teste.com');
    });

    it('should delete aluno successfully', () => {
        cy.get('button[color="warn"]').click();
        cy.url().should('include', '/alunos');
        cy.contains('Aluno excluído com sucesso').should('be.visible');
    });

    it('should cancel deletion and navigate back', () => {
        cy.get('button').contains('Cancelar').click();
        cy.url().should('include', '/alunos');
    });
});