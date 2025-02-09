describe('Workshop Attendance Page', () => {
    beforeEach(() => {
        cy.visit('/workshops/attendance/1');
    });

    it('should display the workshop details', () => {
        cy.get('h2').should('contain', 'Workshop Title');
        cy.get('p').should('contain', 'Descrição: Workshop Description');
        cy.get('p').should('contain', 'Data: 2023-12-31');
        cy.get('p').should('contain', 'Professor: Professor Name');
    });

    it('should display the list of students', () => {
        cy.get('.aluno-card').should('have.length.greaterThan', 0);
    });

    it('should register attendance for a student', () => {
        cy.get('.aluno-card').first().find('mat-slide-toggle').click();
        cy.get('.aluno-card').first().find('mat-slide-toggle').should('have.class', 'mat-checked');
    });

    it('should generate and sign certificates', () => {
        cy.get('button').contains('Gerar e Assinar Certificados').click();
        cy.contains('Certificados gerados e assinados com sucesso').should('be.visible');
    });
});