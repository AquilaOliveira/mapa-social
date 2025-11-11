import React from 'react';
import './AcessoCard.css';

export function AcessoCard({ title, description }) {
  return (
    <div className="acesso-card">
      <h3 className="acesso-card-title">{title}</h3>
      <p className="acesso-card-description">{description}</p>
    </div>
  );
}
