import React from 'react';
import './ServiceCard.css';

export function ServiceCard({ iconSrc, title, subtitle, count }) {
  return (
    <div className="service-card">
      <div className="service-card-icon">
        <img src={iconSrc} alt={title} />
      </div>
      <div className="service-card-content">
        <h3 className="service-card-title">{title}</h3>
        <p className="service-card-subtitle">{subtitle}</p>
      </div>
      {count !== undefined && (
        <div className="service-card-count">
          <span className="count-badge">{count}</span>
        </div>
      )}
    </div>
  );
}
