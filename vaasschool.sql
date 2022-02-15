create database vaasschool;
use vaasschool;

create table `Category`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                varchar(75)  not null,
    `code`                varchar(255) not null unique,
    `description`         varchar(255) not null,
    `explanatory_guide`   varchar(255),
    `active`              bit(1) default 0,
    `order_visualization` int          not null,
    `imagem_path`         varchar(255) not null,
    `color_code`          varchar(7)   not null
);

create table `Subcategory`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                varchar(75)  not null,
    `code`                varchar(255) not null unique,
    `description`         varchar(255) not null,
    `explanatory_guide`   varchar(255),
    `active`              bit(1) default 0,
    `order_visualization` int          not null,
    `category_id`         bigint       not null,
    FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
);

create table `Course`
(
    `id`                       bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                     varchar(75)  not null,
    `code`                     varchar(255) not null unique,
    `estimated_time_to_finish` smallint     not null,
    `visibility`               ENUM ('PUBLIC','PRIVATE') default 'PRIVATE',
    `target_audience`          varchar(255),
    `instructor_name`          varchar(75)  not null,
    `summary`                  text,
    `learned_skills`           varchar(255),
    `subcategory_id`           bigint       not null,
    FOREIGN KEY (`subcategory_id`) REFERENCES `Subcategory` (`id`)
);

create table `Section`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                varchar(75)  not null,
    `code`                varchar(255) not null unique,
    `active`              bit(1) default 0,
    `test`                bit(1) default 0,
    `order_visualization` int,
    `course_id`           bigint       not null,
    FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
);

create table `Activity`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `title`               varchar(255) not null,
    `code`                varchar(255) not null unique,
    `order_visualization` int,
    `active`              bit(1) default 0,
    `type`                ENUM ('Explanation', 'Video', 'Question'),
    `section_id`          bigint       not null,
    FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
);

create table `Explanation`
(
    `id`          bigint PRIMARY KEY AUTO_INCREMENT,
    `text`        varchar(255),
    `activity_id` bigint not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);

create table `Video`
(
    `id`            bigint PRIMARY KEY AUTO_INCREMENT,
    `url`           varchar(255) not null,
    `minutes`       int,
    `transcription` varchar(255),
    `activity_id`   bigint       not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);

create table `Question`
(
    `id`            bigint PRIMARY KEY AUTO_INCREMENT,
    `statement`     varchar(255),
    `question_type` ENUM ('SINGLE_ANSWER','MULTIPLE_ANSWERS','TRUE_FALSE') default 'SINGLE_ANSWER',
    `activity_id`   bigint not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);

create table `Alternative`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `text`                varchar(255) not null,
    `order_visualization` int,
    `correct`             bit(1),
    `justification`       varchar(255),
    `question_id`         bigint       not null,
    FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`)
);

create table `User`
(
    `id`       bigint PRIMARY KEY AUTO_INCREMENT,
    `email`    varchar(255) not null,
    `name`     varchar(255) not null,
    `password` varchar(255) not null
);

create table `Profile`
(
    `id`      bigint PRIMARY KEY AUTO_INCREMENT,
    `name`    varchar(255) not null
);

insert into User(email, name, password)
values ('admin@gmail.com', 'admin', '$2a$10$XZ/wDtEKvMFtfQ9.QuYkbeP96pxUWAkgIhsz2e3ZcJFsUyeszVtx.');
insert into Category (name, code, order_visualization, description, active, imagem_path, color_code)
values ('Programação', 'programacao', 1,
        'Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.',
        true, 'https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png', '#00c86f');
insert into Category (name, code, order_visualization, description, active, imagem_path, color_code)
values ('DevOps', 'devops', 2,
        'Aprenda Git. Entenda a entrega contínua. Estude Linux. Gerencie servidores na nuvem. Explore o mundo de Internet das coisas e da robótica.',
        true, 'https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png', '#f16165');
insert into Category (name, code, order_visualization, description, active, imagem_path, color_code)
values ('Business', 'business', 3, 'Agilidade. Práticas de gestão. Vendas. Liderança.', false,
        'https://www.alura.com.br/assets/api/formacoes/categorias/512/inovacao-gestao-transparent.png', '#ff8c2a');
insert into Subcategory (name, code, order_visualization, description, active, category_id)
values ('Java', 'java', 1,
        'Java é uma grande plataforma presente em todo lugar: de corporações à bancos e governo. Desenvolva aplicações robustas com um back-end e construa APIs.',
        true, (select id from Category where code = 'programacao'));
insert into Subcategory (name, code, order_visualization, description, active, category_id)
values ('Java e Persistência', 'java-e-persistencia', 2, '', true,
        (select id from Category where code = 'programacao'));
insert into Subcategory (name, code, order_visualization, description, active, category_id)
values ('PHP', 'php', 3, 'PHP é uma das linguagens mais utilizadas.', true,
        (select id from Category where code = 'programacao'));
insert into Subcategory (name, code, order_visualization, description, active, category_id)
values ('COBOL', 'cobol', 4, '', false, (select id from Category where code = 'programacao'));
insert into Subcategory (name, code, order_visualization, description, active, category_id)
values ('Builds e Controle de versão', 'builds-e-controle-de-versao', 1,
        'As ferramentas mais utilizadas para desenvolvimento: controle de versão com Git e Github além de build da aplicação através de Maven.',
        true, (select id from Category where code = 'devops'));
insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, instructor_name, summary,
                    learned_skills, subcategory_id)
values ('Git e Github para Sobrevivência', 'git-e-github-para-sobrevivencia', 6, 'PUBLIC',
        'Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para seus projetos com as ferramentas de controle de versão Git e GitHub.',
        'Mario Souto',
        '-O que é Git? <br> *Introdução <br> *Para que serve Git? <br> *Utilidade de um VCS <br> *Instalando o Git <br> *Para saber mais: Instalação <br> *Repositórios <br> *Primeiros passos <br>  <br> -Iniciando os trabalhos <br> *Salvando alterações <br> *Primeira configuração do Git <br> *Para saber mais: git status <br> *Vendo o histórico <br> *Para saber mais: git log <br> *Ignorando arquivos <br> *Para saber mais: Quando commitar <br>  <br> -Compartilhando o trabalho <br> *Repositórios remotos <br> *Servidor Git <br> *Trabalhando com repositórios remotos <br> *Sincronizando os dados <br> *Compartilhamos as alterações <br> *GitHub <br> *Para saber mais: GitHub <br>  <br> -Trabalhando em equipe <br> *Branches <br> *Para saber mais: Ramificações <br> *Unindo o trabalho <br> *Merge de branches <br> *Atualizando a branch <br> *Rebase vs Merge <br> *Resolvendo conflitos <br> *Para saber mais: Conflitos com rebase <br>  <br> -Manipulando as versões <br> *Ctrl+Z no Git <br> *Desfazendo o trabalho <br> *Guardando para depois <br> *Salvando temporariamente <br> *Viajando no tempo <br> *Checkout <br>  <br> -Gerando entregas <br> *Vendo as alterações <br> *Exibição das mudanças com o diff <br> *Tags e releases <br> *Tags no GitHub <br> *Consolidando o seu conhecimento',
        'Descubra o que é Git e Github? <br> Entenda um sistema de controle de versão <br> Salve e recupere seu código em diferentes versões <br> Resolva merges e conflitos <br> Trabalhe com diferentes branches',
        (select id from Subcategory where code = 'builds-e-controle-de-versao'));
insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, instructor_name, summary,
                    learned_skills, subcategory_id)
values ('Java e JPA: Consultas avançadas performance e modelos complexos',
        'java-jpa-consultas-avancadas-performance-modelos-complexos', 10, 'PRIVATE',
        'Pessoas desenvolvedoras que já conhecem o básico de JPA e queiram aprofundar os conhecimentos.',
        'Rodrigo Ferreira',
        '-Mais relacionamentos <br> *Apresentação <br> *Mapeando novas entidades <br> *Relacionamentos many-to-many <br> *Relacionamentos bidirecionais <br> *Teste do relacionamento bidirecional <br>  <br> -Consultas avançadas <br> *Consultas com funções de agregação <br> *Consultas para relatórios <br> *Consultas com select new <br> *Utilizando Named Queries <br>  <br> -Performance de consultas <br> *Entendendo Lazy e Eager <br> *Consultas com Join Fetch <br>  <br> -Criteria API <br> *Consultas com parâmetros dinâmicos <br> *Consultas com Criteria API <br>  <br> -Outros tópicos <br> *Simplificando entidades com Embeddable <br> *Mapeamento de herança <br> *Mapeamento de chaves compostas <br> *Conclusão',
        'Saiba como modelar corretamente relacionamentos bidirecionais <br> Aprenda a utilizar o recurso de select new para realizar consultas avançadas <br> Entenda a diferença entre relacionamentos EAGER e LAZY <br> Conheça o recurso de join fetch para planejar queries <br> Conheça a API de Criteria da JPA <br> Saiba como mapear entidades que utilizam herança e chave composta',
        (select id from Subcategory where code = 'java-e-persistencia'));
insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, instructor_name, summary,
                    learned_skills, subcategory_id)
values ('Java OO: Introdução à Orientação a Objetos', 'java-introducao-orientacao-objetos', 8, 'PUBLIC',
        'Desenvolvedores que estão começando com Java e querem aprender mais sobre OO.', 'Paulo Silveira',
        '-O problema do paradigma procedural <br> *Paradigma procedural vs Objetos <br> *Idéia central do paradigma OO <br> *Cheiro procedural <br>  <br> -Começando com Orientação a Objetos <br> *Primeira classe - Contas <br> *Características dos objetos <br> *Instanciação atributos e referências <br> *Definindo tipos <br> *Segunda Instância <br> *Valores default de atributos <br> *Definindo valor de atributos <br> *Referências vs Objetos <br> *Referências de objetos <br> *Mão na massa: Criando as primeiras classes <br>  <br> -Definindo comportamento <br> *Nosso primeiro método <br> *Sobre métodos <br> *Como chamar um método? <br> *Você conhece o this? <br> *Métodos com retorno <br> *Métodos validos <br> *Onde usar o this? <br> *Métodos com referência e mais retorno <br> *Declaração do método <br> *Mão na massa: Criando métodos <br>  <br> -Composição de objetos <br> *Composição de Objetos <br> *Extraindo o que é comum  <br> *Referência Null <br> *Problema não esperado <br> *Solucionando o problema no código  <br> *O que aprendemos? <br> *Mão na massa: Referências <br>  <br> -Encapsulamento e visibilidade <br> *Atributos privados e encapsulamento <br> *Público x Privado <br> *Getters e Setters <br> *Criando Getters e Setters <br> *Getters e Setters de referência <br> *Vantagens de atributos privados <br> *Mão na massa: Criando Getters e Setters <br> *Para saber mais: Cuidado com o Modelo Anêmico <br>  <br> -Construtores e membros estáticos <br> *Construtores <br> *Utilizando Construtores <br> *Aonde está o erro? <br> *Static <br> *Por que não soma? <br> *Mãos na massa: Criando construtores e variáveis estáticas <br> *Para saber mais: Reaproveitamento entre construtores',
        'Domine o paradigma de programação mais usado no mercado de trabalho <br> Entenda o que são referências e objetos <br> Use atributos métodos da instancia e da classe <br> Define objetos através de construtores <br> Aprenda sobre encapsulamento',
        (select id from Subcategory where code = 'java'));
insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, instructor_name, summary,
                    learned_skills, subcategory_id)
values ('Java JRE e JDK: Escreva o seu primeiro código com Eclipse', 'java-primeiros-passos', 8, 'PUBLIC',
        'Desenvolvedores que querem começar com a linguagem Java.', 'Paulo Silveira',
        '-O que é Java? <br> *A plataforma Java <br> *Benefício da JVM <br> *Quais características? <br> *Quais sistemas? <br> *Bytecode vs EXE? <br> *Sobre o Bytecode <br> *Para saber mais: o nome Bytecode <br>  <br> -Instalação e o primeiro programa <br> *Instalação do JDK no Windows <br> *JRE vs JDK <br> *Para saber mais: JVM vs JRE vs JDK <br> *Compile e rode seu primeiro programa Java <br> *Entrada da aplicação <br> *Sobre a compilação e execução <br> *Compilar e executar <br> *Mão na massa: Instalando o JDK <br> *Mão na massa: Escrevendo nosso primeiro código <br>  <br> -Começando com Eclipse <br> *Instalando o Eclipse <br> *Mão na massa: Instale a IDE Eclipse <br> *Sobre IDEs e editores <br> *Dentro do Eclipse IDE <br> *Nosso programa rodando no Eclipse <br> *Mão na massa: Rodando nosso programa no Eclipse <br> *Projeto Java <br> *Sobre a View Navigator <br>  <br> -Tipos e variáveis <br> *Tipo inteiro: int <br> *Criando uma variável numérica <br> *Mão na massa: Utilizando o tipo int <br> *Tipo flutuante: double <br> *Operações entre numeros <br> *Mão na massa: Utilizando o tipo double <br> *Conversões e outros tipos <br> *Imprimindo texto e números <br> *Mão na massa: Algumas conversões em Java <br> *Para saber mais: Type Casting <br>  <br> -Trabalhando com caracteres <br> *Char e String <br> *Declarando String e char <br> *Qual será o resultado? <br> *Variáveis guardam valores <br> *Concatenação de String e inteiros <br> *Mão na massa: Praticando char e String <br>  <br> -Praticando condicionais <br> *Testes com IF <br> *Trabalhando com if <br> *Mão na massa: A condicional if <br> *Boolean condicionais <br> *Tipo boolean <br> *Operador lógico <br> *Mão na massa: Um pouco mais de if <br> *Escopo e inicialização de variáveis <br> *Declaração da variável <br> *Mão na massa: Escopo de variáveis <br> *Opcional: Alíquota com ifs <br> *Para saber mais: o comando switch <br>  <br> -Controlando fluxo com laços <br> *Laço com while <br> *Enquanto isso o while... <br> *Fixando o laço while <br> *Escopo nos laços <br> *Um erro de compilação... <br> *Laço com for <br> *Transformando while em for <br> *Mão na massa: Laços <br> *Laços encadeados <br> *Mais laços com break <br> *Fixando o comando break <br> *Exercitando laços aninhados e break <br> *Mão na massa: Aprofundando laços <br> *Desafio Opcional: Múltiplos de 3 <br> *Desafio opcional: Fatorial',
        'JVM? JDK? JRE? Aprenda o que são essas siglas? <br> Compile e execute código java <br> Aprenda a usar Eclipse <br> Veja como usar variáveis e controle de fluxo <br> Conheça os principais tipos do Java',
        (select id from Subcategory where code = 'java'));