import { Turma } from "../../turmas/turma/turma.model";

export class Aluno {
    id: number;
    nome: string;
    matricula: string;
    dataNascimento: string;
    turma: string;
    serie: string;
    sala: string;
    turno: string;
    alunoBolsista: boolean;
    urlFoto: string;
    nomeResponsavel: string;
    cpf: string;
    email: string;
    email2: string;
    telefoneFixo: string;
    telefoneCelular: string;
    enviarEmail: boolean;
    enviarMensagem: boolean;
    anoLetivo: number;
}