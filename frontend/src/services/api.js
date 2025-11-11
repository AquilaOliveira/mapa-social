// Configuração base da API
const API_BASE_URL = 'http://localhost:8080';

// Função auxiliar para fazer requisições
async function request(endpoint, options = {}) {
  const url = `${API_BASE_URL}${endpoint}`;
  
  const config = {
    headers: {
      'Content-Type': 'application/json',
      ...options.headers,
    },
    ...options,
  };

  try {
    const response = await fetch(url, config);
    
    // Se for 204 No Content, retorna null
    if (response.status === 204) {
      return null;
    }
    
    // Tenta parsear JSON
    const data = await response.json();
    
    if (!response.ok) {
      throw new Error(data.message || `Erro: ${response.status}`);
    }
    
    return data;
  } catch (error) {
    console.error('Erro na requisição:', error);
    throw error;
  }
}

// ========== USUÁRIOS ==========

export const usuarioAPI = {
  // Cadastrar novo usuário
  cadastrar: async (usuario) => {
    return request('/usuarios/cadastro', {
      method: 'POST',
      body: JSON.stringify(usuario),
    });
  },

  // Login
  login: async (email, senhaHash) => {
    return request('/usuarios/login', {
      method: 'POST',
      body: JSON.stringify({ email, senhaHash }),
    });
  },

  // Buscar todos os usuários
  buscarTodos: async () => {
    return request('/usuarios');
  },

  // Buscar usuário por ID
  buscarPorId: async (id) => {
    return request(`/usuarios/${id}`);
  },
};

// ========== SERVIÇOS SOCIAIS ==========

export const servicoSocialAPI = {
  // Listar todos os serviços
  buscarTodos: async () => {
    return request('/servicos');
  },

  // Buscar serviço por ID
  buscarPorId: async (id) => {
    return request(`/servicos/${id}`);
  },

  // Criar novo serviço
  criar: async (servico) => {
    return request('/servicos', {
      method: 'POST',
      body: JSON.stringify(servico),
    });
  },

  // Atualizar serviço
  atualizar: async (id, servico) => {
    return request(`/servicos/${id}`, {
      method: 'PUT',
      body: JSON.stringify(servico),
    });
  },

  // Deletar serviço
  deletar: async (id) => {
    return request(`/servicos/${id}`, {
      method: 'DELETE',
    });
  },
};

// ========== HISTÓRICO ==========

export const historicoAPI = {
  // Registrar acesso
  registrar: async (historico) => {
    return request('/historico', {
      method: 'POST',
      body: JSON.stringify(historico),
    });
  },

  // Buscar histórico por usuário
  buscarPorUsuario: async (usuarioId) => {
    return request(`/historico/usuario/${usuarioId}`);
  },

  // Buscar histórico por ID
  buscarPorId: async (id) => {
    return request(`/historico/${id}`);
  },

  // Deletar histórico
  deletar: async (id) => {
    return request(`/historico/${id}`, {
      method: 'DELETE',
    });
  },
};

// ========== FAVORITOS ==========

export const favoritoAPI = {
  // Adicionar favorito
  adicionar: async (favorito) => {
    return request('/favoritos', {
      method: 'POST',
      body: JSON.stringify(favorito),
    });
  },

  // Buscar favoritos por usuário
  buscarPorUsuario: async (usuarioId) => {
    return request(`/favoritos/usuario/${usuarioId}`);
  },

  // Remover favorito
  remover: async (usuarioId, servicoSocialId) => {
    return request(`/favoritos/${usuarioId}/${servicoSocialId}`, {
      method: 'DELETE',
    });
  },
};

// ========== CATEGORIAS ==========

export const categoriaAPI = {
  // Listar todas as categorias
  buscarTodas: async () => {
    return request('/categorias');
  },

  // Buscar categoria por ID
  buscarPorId: async (id) => {
    return request(`/categorias/${id}`);
  },

  // Criar nova categoria
  criar: async (categoria) => {
    return request('/categorias', {
      method: 'POST',
      body: JSON.stringify(categoria),
    });
  },
};

// ========== ENDEREÇOS ==========

export const enderecoAPI = {
  // Listar todos os endereços
  buscarTodos: async () => {
    return request('/enderecos');
  },

  // Buscar endereço por ID
  buscarPorId: async (id) => {
    return request(`/enderecos/${id}`);
  },

  // Criar novo endereço
  criar: async (endereco) => {
    return request('/enderecos', {
      method: 'POST',
      body: JSON.stringify(endereco),
    });
  },
};

  // ========== SUGESTÕES ==========

  export const sugestaoAPI = {
    // Criar nova sugestão
    criar: async (sugestao) => {
      return request('/sugestoes', {
        method: 'POST',
        body: JSON.stringify(sugestao),
      });
    },

    // Buscar todas as sugestões
    buscarTodas: async () => {
      return request('/sugestoes');
    },

    // Buscar sugestão por ID
    buscarPorId: async (id) => {
      return request(`/sugestoes/${id}`);
    },

    // Buscar sugestões por usuário
    buscarPorUsuario: async (usuarioId) => {
      return request(`/sugestoes/usuario/${usuarioId}`);
    },

    // Buscar sugestões pendentes
    buscarPendentes: async () => {
      return request('/sugestoes/pendentes');
    },

    // Aprovar sugestão
    aprovar: async (id) => {
      return request(`/sugestoes/${id}/aprovar`, {
        method: 'PUT',
      });
    },

    // Rejeitar sugestão
    rejeitar: async (id) => {
      return request(`/sugestoes/${id}/rejeitar`, {
        method: 'PUT',
      });
    },

    // Deletar sugestão
    deletar: async (id) => {
      return request(`/sugestoes/${id}`, {
        method: 'DELETE',
      });
    },
  };

export default {
  usuario: usuarioAPI,
  servicoSocial: servicoSocialAPI,
  historico: historicoAPI,
  favorito: favoritoAPI,
  categoria: categoriaAPI,
  endereco: enderecoAPI,
    sugestao: sugestaoAPI,
};
