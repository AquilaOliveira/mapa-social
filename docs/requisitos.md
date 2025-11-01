### `/docs/requisitos.md`
```markdown
# Requisitos da Aplicação — Mapa Social

Este documento apresenta os requisitos funcionais e não funcionais definidos para o sistema Mapa Social, conforme o projeto desenvolvido na disciplina de Análise Orientada a Objetos (BRAAOOB) do IFSP Bragança Paulista.

---

## 1. Requisitos Funcionais

Os requisitos foram agrupados em módulos, conforme os casos de uso definidos na análise.

### Módulo 1 — Manter Serviços Sociais

| Nº | Descrição |
|----|------------|
| REQ-01 | Solicitar e processar a permissão de acesso à localização do usuário. |
| REQ-02 | Exibir um mapa interativo com os serviços sociais disponíveis. |
| REQ-03 | Permitir filtrar serviços por categoria. |
| REQ-04 | Permitir buscar serviços por palavra-chave. |
| REQ-05 | Exibir detalhes de um serviço social (nome, endereço, contato). |
| REQ-06 | Permitir obter rotas até o local do serviço. |
| REQ-07 | Disponibilizar formulário para sugestão de novos serviços sociais. |
| REQ-08 | Validar dados do formulário de sugestão. |
| REQ-09 | Permitir acompanhar o status das sugestões. |
| REQ-10 | Exibir lista de sugestões pendentes para o administrador. |
| REQ-11 | Permitir aprovar ou recusar sugestões. |
| REQ-12 | Permitir inserir justificativa de recusa. |
| REQ-13 | Permitir editar informações de um serviço sugerido. |

---

### Módulo 2 — Ver Rota Para o Local

| Nº | Descrição |
|----|------------|
| REQ-14 | Permitir visualizar a rota até o serviço a partir da tela de detalhes. |
| REQ-15 | Detectar aplicativos de mapas instalados e oferecer opções de escolha. |

---

### Módulo 3 — Fazer Login

| Nº | Descrição |
|----|------------|
| REQ-16 | Autenticar usuários via e-mail ou contas externas. |
| REQ-17 | Permitir recuperação e validação de nova senha. |
| REQ-18 | Fornecer mensagens de confirmação ou erro durante login. |
| REQ-19 | Redirecionar o usuário após login bem-sucedido. |

---

### Módulo 4 — Manter Administradores/Usuário

| Nº | Descrição |
|----|------------|
| REQ-20 | Permitir criação de conta de usuário comum. |
| REQ-21 | Permitir login e autenticação de credenciais. |
| REQ-22 | Permitir recuperação de senha por e-mail. |
| REQ-23 | Permitir edição ou exclusão da própria conta. |
| REQ-24 | Permitir ao usuário TI criar administradores. |
| REQ-25 | Permitir ao usuário TI atribuir ou revogar funções administrativas. |
| REQ-26 | Permitir ao usuário TI editar ou desativar contas de administradores. |
| REQ-27 | Validar se a operação é compatível com o perfil do usuário. |

---

### Módulo 5 — Gerenciar Avisos e Estatísticas

| Nº | Descrição |
|----|------------|
| REQ-28 | Permitir cadastro, edição e remoção de avisos. |
| REQ-29 | Permitir visualização de estatísticas administrativas com filtros. |

---

### Módulo 6 — Consultar Favoritos

| Nº | Descrição |
|----|------------|
| REQ-30 | Permitir salvar e remover serviços dos favoritos. |
| REQ-31 | Exibir lista de favoritos do usuário autenticado. |
| REQ-32 | Permitir acompanhar status de sugestões enviadas. |
| REQ-33 | Exibir histórico de serviços acessados. |
| REQ-34 | Permitir envio de novas sugestões de serviços sociais. |
| REQ-35 | Vincular sugestões enviadas a usuários autenticados. |
| REQ-36 | Exibir justificativas relacionadas às sugestões analisadas. |

---

## 2. Requisitos Não Funcionais

| Nº | Categoria | Descrição |
|----|------------|------------|
| RNF-01 | Integração com API de Mapas | O sistema deve manter comunicação estável e segura com a API de mapas. |
| RNF-02 | Registro de Logs | Todas as ações de usuários e administradores devem ser registradas em logs de auditoria. |
| RNF-03 | Criptografia de Senhas | Todas as senhas devem ser criptografadas antes do armazenamento. |
| RNF-04 | Segurança de Dados | Os dados de usuários devem ser protegidos contra acesso não autorizado. |
| RNF-05 | Compatibilidade de Navegadores | O sistema deve funcionar nos principais navegadores modernos. |
| RNF-06 | Responsividade | A interface deve se adaptar automaticamente a diferentes tamanhos de tela. |
| RNF-07 | Usabilidade | O sistema deve possuir interface intuitiva e acessível. |
| RNF-08 | Desempenho | O carregamento de páginas e mapas deve ocorrer em até 3 segundos. |
| RNF-09 | Disponibilidade | O sistema deve estar disponível 24/7, exceto em manutenção programada. |
| RNF-10 | Manutenibilidade | O código deve ser modular e documentado. |
| RNF-11 | Backup e Recuperação | O sistema deve realizar cópias de segurança automáticas diariamente. |
| RNF-12 | Acessibilidade | O sistema deve seguir as diretrizes WCAG. |
| RNF-13 | Mensagens de Erro Claras | O sistema deve exibir mensagens compreensíveis e orientativas. |
| RNF-14 | Confiabilidade | O sistema deve garantir integridade dos dados em caso de falhas. |
| RNF-15 | Privacidade | O sistema não deve expor informações pessoais sem consentimento. |

---

Fonte: Autoria própria (2025) – Projeto Mapa Social – IFSP Bragança Paulista
