export enum SerieEnum {
    PRIMEIRO_ANO_FUNDAMENTAL = "1º Ano do Ensino Fundamental",
    SEGUNDO_ANO_FUNDAMENTAL = "2º Ano do Ensino Fundamental",
    TERCEIRO_ANO_FUNDAMENTAL = "3º Ano do Ensino Fundamental",
    QUARTO_ANO_FUNDAMENTAL = "4º Ano do Ensino Fundamental",
    QUINTO_ANO_FUNDAMENTAL = "5º Ano do Ensino Fundamental",
    SEXTO_ANO_FUNDAMENTAL = "6º Ano do Ensino Fundamental",
    SETIMO_ANO_FUNDAMENTAL = "7º Ano do Ensino Fundamental",
    OITAVO_ANO_FUNDAMENTAL = "8º Ano do Ensino Fundamental",
    NONO_ANO_FUNDAMENTAL = "9º Ano do Ensino Fundamental",
    PRIMEIRO_ANO_MEDIO = "1º Ano do Ensino Médio",
    SEGUNDO_ANO_MEDIO = "2º Ano do Ensino Médio",
    TERCEIRO_ANO_MEDIO = "3º Ano do Ensino Médio"
}

export const SerieEnumKeys = Object.keys(SerieEnum) as Array<keyof typeof SerieEnum>;