describe('Workshop Create Form', () => {
    beforeEach(() => {
        cy.visit('/workshops/create');
    });

    it('should display the workshop create form', () => {
        cy.get('input[formControlName="titulo"]').should('be.visible');
        cy.get('textarea[formControlName="descricao"]').should('be.visible');
        cy.get('input[formControlName="data"]').should('be.visible');
        cy.get('input[formControlName="horaInicio"]').should('be.visible');
        cy.get('input[formControlName="horaFim"]').should('be.visible');
        cy.get('input[formControlName="nome"]').should('be.visible');
        cy.get('input[formControlName="email"]').should('be.visible');
        cy.get('input[formControlName="telefoneContato"]').should('be.visible');
        cy.get('input[formControlName="especializacao"]').should('be.visible');
        cy.get('input[formControlName="instituicaoVinculo"]').should('be.visible');
    });

    it('should show error messages for invalid input', () => {
        cy.get('button[type="submit"]').click();
        cy.get('mat-error').should('contain', 'Título é obrigatório');
        cy.get('mat-error').should('contain', 'Descrição é obrigatória');
        cy.get('mat-error').should('contain', 'Data é obrigatória');
        cy.get('mat-error').should('contain', 'Hora Início é obrigatória');
        cy.get('mat-error').should('contain', 'Hora Fim é obrigatória');
        cy.get('mat-error').should('contain', 'Nome do Professor é obrigatório');
        cy.get('mat-error').should('contain', 'Email do Professor é obrigatório');
        cy.get('mat-error').should('contain', 'Telefone de Contato do Professor é obrigatório');
        cy.get('mat-error').should('contain', 'Especialização do Professor é obrigatória');
        cy.get('mat-error').should('contain', 'Instituição de Vínculo do Professor é obrigatória');
    });

    it('should fill the form and submit successfully', () => {
        cy.get('input[formControlName="titulo"]').type('Workshop Title');
        cy.get('textarea[formControlName="descricao"]').type('Workshop Description');
        cy.get('input[formControlName="data"]').type('2023-12-31');
        cy.get('input[formControlName="horaInicio"]').type('09:00', { force: true });
        cy.get('input[formControlName="horaFim"]').type('17:00', { force: true });
        cy.get('input[formControlName="nome"]').type('Professor Name', { force: true });
        cy.get('input[formControlName="email"]').type('professor@example.com', { force: true });
        cy.get('input[formControlName="telefoneContato"]').type('(11) 99999-9999', { force: true });
        cy.get('input[formControlName="especializacao"]').type('Specialization', { force: true });
        cy.get('input[formControlName="instituicaoVinculo"]').type('Institution', { force: true });
        cy.get('button[type="submit"]').click({ force: true });
        cy.get('.success-message').should('contain', 'Workshop created successfully');
    });
});