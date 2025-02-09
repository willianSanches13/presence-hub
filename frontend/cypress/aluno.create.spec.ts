// cypress/aluno.create.spec.ts
describe('Aluno Create Page', () => {
    beforeEach(() => {
        cy.visit('/alunos/create');
    });

    it('should display the aluno create form', () => {
        cy.get('input[formControlName="nome"]').should('be.visible');
        cy.get('mat-select[formControlName="serie"]').should('be.visible');
        cy.get('input[formControlName="telefoneContato"]').should('be.visible');
        cy.get('input[formControlName="email"]').should('be.visible');
        cy.get('input[formControlName="dataNascimento"]').should('be.visible');
        cy.get('input[formControlName="instituicaoDeEnsino"]').should('be.visible');
        cy.get('input[formControlName="nomeResponsavel"]').should('be.visible');
        cy.get('input[formControlName="telefoneResponsavel"]').should('be.visible');
        cy.get('input[formControlName="matriculaProjeto"]').should('be.visible');
        cy.get('input[formControlName="dataInscricao"]').should('be.visible');
        cy.get('textarea[formControlName="observacoes"]').should('be.visible');
        cy.get('input[formControlName="cep"]').should('be.visible');
        cy.get('input[formControlName="logradouro"]').should('be.visible');
        cy.get('input[formControlName="bairro"]').should('be.visible');
        cy.get('input[formControlName="cidade"]').should('be.visible');
        cy.get('input[formControlName="uf"]').should('be.visible');
        cy.get('input[formControlName="numero"]').should('be.visible');
        cy.get('input[formControlName="complemento"]').should('be.visible');
    });
    it('should show error messages for invalid input', () => {
        cy.get('button[type="submit"]').click();
        cy.get('mat-error').should('contain', 'Nome é obrigatório');
        cy.get('mat-error').should('contain', 'Série é obrigatória');
        cy.get('mat-error').should('contain', 'Telefone de Contato é obrigatório');
        cy.get('mat-error').should('contain', 'Email é obrigatório');
        cy.get('mat-error').should('contain', 'Data de Nascimento é obrigatória');
        cy.get('mat-error').should('contain', 'Instituição de Ensino é obrigatória');
        cy.get('mat-error').should('contain', 'Nome do Responsável é obrigatório');
        cy.get('mat-error').should('contain', 'Telefone do Responsável é obrigatório');
        cy.get('mat-error').should('contain', 'Matrícula no Projeto é obrigatória');
        cy.get('mat-error').should('contain', 'Data de Inscrição é obrigatória');
        cy.get('mat-error').should('contain', 'CEP é obrigatório');
        cy.get('mat-error').should('contain', 'Logradouro é obrigatório');
        cy.get('mat-error').should('contain', 'Bairro é obrigatório');
        cy.get('mat-error').should('contain', 'Cidade é obrigatória');
        cy.get('mat-error').should('contain', 'UF é obrigatório');
        cy.get('mat-error').should('contain', 'Número é obrigatório');
    });

    it('should create aluno successfully with valid input', () => {
        cy.get('input[formControlName="nome"]').type('Aluno Teste');
        cy.get('mat-select[formControlName="serie"]').click().get('mat-option').contains('1º Ano').click();
        cy.get('input[formControlName="telefoneContato"]').type('(11) 99999-9999');
        cy.get('input[formControlName="email"]').type('aluno@teste.com');
        cy.get('input[formControlName="dataNascimento"]').type('2000-01-01');
        cy.get('input[formControlName="instituicaoDeEnsino"]').type('Escola Teste');
        cy.get('input[formControlName="nomeResponsavel"]').type('Responsável Teste');
        cy.get('input[formControlName="telefoneResponsavel"]').type('(11) 99999-9999');
        cy.get('input[formControlName="matriculaProjeto"]').type('12345');
        cy.get('input[formControlName="dataInscricao"]').type('2023-01-01');
        cy.get('textarea[formControlName="observacoes"]').type('Nenhuma observação');
        cy.get('input[formControlName="cep"]').type('12345-678');
        cy.get('input[formControlName="logradouro"]').type('Rua Teste');
        cy.get('input[formControlName="bairro"]').type('Bairro Teste');
        cy.get('input[formControlName="cidade"]').type('Cidade Teste');
        cy.get('input[formControlName="uf"]').type('SP');
        cy.get('input[formControlName="numero"]').type('123');
        cy.get('input[formControlName="complemento"]').type('Apto 1');
        cy.get('button[type="submit"]').click();
        cy.url().should('include', '/alunos');
    });
});