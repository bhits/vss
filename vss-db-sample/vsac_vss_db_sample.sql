--
-- Insert data for table `value_set_category_system`
--
INSERT INTO `value_set_category_system` VALUES (1, 'http://hl7.org/fhir/v3/ActCode');

--
-- Insert data for table `code_system`
--
INSERT INTO `code_system` VALUES
  (1, 'SNOMED CT ', 'SNOMED-Clinical Terms', '2.16.840.1.113883.6.96',
   'Systematized Nomenclature of Medicine-Clinical Terms'),
  (2, 'ICD-10-CM ', 'ICD-10-Clinical Modification', '2.16.840.1.113883.6.90',
   'The International Classification of Diseases, 10th Revision, Clinical Modification'),
  (3, 'ICD-9-CM ', 'ICD-9-Clinical Modification', '2.16.840.1.113883.6.103',
   'The International Classification of Diseases, 9th Revision, Clinical Modification'),
  (4, 'LOINC ', 'LOINC', '2.16.840.1.113883.6.1', 'Logical Observation Identifier Names and Codes'),
  (5, 'PH_RxNorm', 'RxNorm', '2.16.840.1.113883.6.88',
   'Catalog of the standard names given to clinical drugs and drug delivery devices'),
  (6, 'HCPCS', 'HCPCS', '2.16.840.1.113883.6.14', 'Healthcare Common Procedure Coding System'),
  (7, 'CPT-4', 'CPT-4', '2.16.840.1.113883.6.12',
   'American Medical Association\'s Current Procedure Terminology 4 (CPT-4) codes');