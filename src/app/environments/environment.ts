export const environment = {
  name: "dev",
  production: false,

  rechercherClasses: 'http://localhost:8080/classe',
  enregistrerClasse: 'http://localhost:8080/classe/create',
  updateClasse : 'http://localhost:8080/classe/update',
  supprimerClasse: 'http://localhost:8080/api/classe/supprimer',

  rechercherEtudiants: 'http://localhost:8080/student',
  enregistrerEtudiant: 'http://localhost:8080/student/create',
  supprimerEtudiant: 'http://localhost:8080/api/etudiant/supprimer',

  rechercherEtudiantsDisponibles: 'http://localhost:8080/student/withoutClasse'
};