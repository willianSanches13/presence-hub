describe('User Create Form', () => {
    beforeEach(() => {
        cy.visit('/users');
    });

    it('should display the user create form', () => {
        cy.get('input[formControlName="login"]').should('be.visible');
        cy.get('input[formControlName="password"]').should('be.visible');
        cy.get('input[formControlName="confirmPassword"]').should('be.visible');
        cy.get('input[formControlName="email"]').should('be.visible');
    });

    it('should show validation errors for required fields', () => {
        cy.get('button[type="submit"]').click();
        cy.get('mat-error').should('contain', 'Senha é obrigatória');
        cy.get('mat-error').should('contain', 'Confirmação de senha é obrigatória');
    });

    it('should show validation error for password mismatch', () => {
        cy.get('input[formControlName="password"]').type('Password123!');
        cy.get('input[formControlName="confirmPassword"]').type('Password1234!');
        cy.get('button[type="submit"]').click();
        cy.get('mat-error').should('contain', 'As senhas não coincidem');
    });

    it('should create user successfully', () => {
        cy.get('input[formControlName="login"]').type('newuser');
        cy.get('input[formControlName="password"]').type('Password123!');
        cy.get('input[formControlName="confirmPassword"]').type('Password123!');
        cy.get('input[formControlName="email"]').type('newuser@example.com');
        cy.get('button[type="submit"]').click();
        cy.get('.success-message', { timeout: 10000 }).should('contain', 'Usuário criado com sucesso');
    });

    it('should cancel and navigate back', () => {
        cy.get('button').contains('Cancelar').click();
        cy.url().should('include', '/users');
    });
});