export const environment = {
  name: "dev",
  production: false,

  rechercherClasses: 'http://localhost:8080/classe',
  enregistrerClasse: 'http://localhost:8080/classe/create',
  updateClasse: 'http://localhost:8080/classe/update',
  supprimerClasse: 'http://localhost:8080/classe/delete',

  rechercherEtudiants: 'http://localhost:8080/student',
  enregistrerEtudiant: 'http://localhost:8080/student/create',
  updateEtudiant : 'http://localhost:8080/student/update',
  supprimerEtudiant: 'http://localhost:8080/student/delete',

  rechercherMatieres : 'http://localhost:8080/subject',
  enregistrerMatiere : 'http://localhost:8080/subject/create',
  updateMatiere : 'http://localhost:8080/subject/update',
  supprimerMatiere : 'http://localhost:8080/subject/delete',
  
  rechercherDevoirs : 'http://localhost:8080/homework',
  enregistrerDevoir : 'http://localhost:8080/homework/create',
  updateDevoir : 'http://localhost:8080/homework/update',
  supprimerDevoir : 'http://localhost:8080/homework/delete',

  rechercherEtudiantsDisponibles: 'http://localhost:8080/student/withoutClasse'
};
