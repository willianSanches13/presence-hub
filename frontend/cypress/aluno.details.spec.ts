describe('Aluno Details Page', () => {
    beforeEach(() => {
        cy.visit('/alunos/details/1');
    });

    it('should display the aluno details', () => {
        cy.get('mat-card-title').should('contain', 'Detalhes do Aluno');
        cy.get('mat-list-item').should('contain', 'Nome:').and('contain', 'Série:').and('contain', 'Telefone de Contato:')
            .and('contain', 'Email:').and('contain', 'Data de Nascimento:').and('contain', 'Instituição de Ensino:')
            .and('contain', 'Nome do Responsável:').and('contain', 'Telefone do Responsável:')
            .and('contain', 'Matrícula no Projeto:').and('contain', 'Data de Inscrição:').and('contain', 'Observações:');
    });

    it('should display the aluno address', () => {
        cy.get('h3').should('contain', 'Endereço');
        cy.get('mat-list-item').should('contain', 'CEP:').and('contain', 'Logradouro:').and('contain', 'Bairro:')
            .and('contain', 'Cidade:').and('contain', 'UF:').and('contain', 'Número:').and('contain', 'Complemento:');
    });

    it('should display the aluno certificates', () => {
        cy.get('h3').should('contain', 'Certificados');
        cy.get('mat-list-item').should('contain', 'Id:').and('contain', 'Data:').and('contain', 'Workshop:');
        cy.get('a').should('have.attr', 'href').and('include', 'cloud_download');
    });

    it('should navigate back when clicking the cancel button', () => {
        cy.get('button.cancel-button').click();
        cy.url().should('include', '/alunos');
    });
});